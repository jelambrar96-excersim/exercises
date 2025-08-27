import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import org.json.JSONArray;
import org.json.JSONObject;

class RestApi {

    private ArrayList<User> usersdb;

    RestApi(User... users) {
        this.usersdb = new ArrayList<User>(Arrays.asList(users));
    }

    String get(String url) {
        if (url.equals("/users")) {
            JSONArray arrayUser = new JSONArray();
            for (User u: usersdb) {
                arrayUser.put(user2JsonObject(u));
            }
            JSONObject output = new JSONObject();
            output.put("users", arrayUser);
            return output.toString();
        }
        return null;
    }

    String get(String url, JSONObject payload) {
        if (url.equals("/users")) {
            List<String> names = payload.getJSONArray("users").toList().stream()
                    .map(o -> (String)o).toList();
            JSONArray arrayUser = new JSONArray();
            for(User u: usersdb) {
                if (!names.contains(u.name())) { continue; }
                arrayUser.put(user2JsonObject(u));
            }
            JSONObject output = new JSONObject();
            output.put("users", arrayUser);
            return output.toString();
        }
        return null;
    }

    String post(String url, JSONObject payload) {
        if (url.equals("/add")) {
            User.Builder bu = new User.Builder().setName(payload.getString("user"));
            User u = bu.build();
            this.usersdb.add(u);
            return user2JsonObject(u).toString();
        }

        if(url.equals("/iou")) {
            String lenderName = payload.getString("lender");
            String borrowerName = payload.getString("borrower");
            double amount = payload.getDouble("amount");

            Integer indLender = IntStream.range(0, this.usersdb.size())
                    .filter(i -> this.usersdb.get(i).name() == lenderName)
                    .findFirst().orElse(-1);
            if (indLender == -1) { return null; }
            User lenderUser = this.usersdb.get(indLender);
            
            Integer indBorrower= IntStream.range(0, this.usersdb.size())
                    .filter(i -> this.usersdb.get(i).name() == borrowerName)
                    .findFirst().orElse(-1);
            if (indBorrower == -1) { return null; }
            User borrowerUser = this.usersdb.get(indBorrower);

            if (indBorrower == indLender) { return null; }

            User newLender = exchange(lenderUser, borrowerUser, amount);
            User newBorrower = exchangeNeg(lenderUser, borrowerUser, amount);

            this.usersdb.remove(indLender.intValue());
            this.usersdb.add(indLender.intValue(), newLender);

            this.usersdb.remove(indBorrower.intValue());
            this.usersdb.add(indBorrower.intValue(), newBorrower);

            List<User> listOut = new ArrayList<User>(Arrays.asList(newLender, newBorrower));
            listOut.sort((o0, o1) -> o0.name().compareTo(o1.name()));

            JSONArray arrayUsers = new JSONArray();
            for (User jo: listOut) {
                arrayUsers.put(user2JsonObject(jo));
            }
            JSONObject output = new JSONObject();
            output.put("users", arrayUsers);
            return output.toString();
        }

        return null;
    }

    private JSONObject user2JsonObject(User u) {
        JSONObject ujson = new JSONObject();
        ujson.put("name", u.name());
        JSONObject owes = new JSONObject();
        for (Iou o: u.owes()) {
            if (o.amount <= 0) { continue; }
            owes.put(o.name, o.amount);
        }
        ujson.put("owes", owes);
        JSONObject owersby = new JSONObject();
        for (Iou o: u.owedBy()) {
            if (o.amount <= 0) { continue; }
            owersby.put(o.name, o.amount);
        }
        ujson.put("owedBy", owersby);
        double balance = u.owedBy().stream().mapToDouble(x -> x.amount).sum()
                - u.owes().stream().mapToDouble(x -> x.amount).sum();
        ujson.put("balance", balance);
        return ujson;
    }

    private User exchange(User lenderUser, User borrowerUser, double amount) {

        User.Builder bu = new User.Builder().setName(lenderUser.name());
        String borrowerName = borrowerUser.name();
        double amountL = amount;

        for (Iou tempIou : lenderUser.owes()) {
            if (tempIou.name != borrowerName) {
                bu.owes(tempIou.name, tempIou.amount);
                continue;
            }
            if (tempIou.amount > amountL) {
                bu.owes(tempIou.name, tempIou.amount - amountL);
                amountL = 0;
            }
            else {
                amountL -= tempIou.amount;
            }
        }

        boolean found = false;
        for (Iou tempIou : lenderUser.owedBy()) {
            if (tempIou.name != borrowerName) {
                bu.owedBy(tempIou.name, tempIou.amount);
                continue;
            }
            found = true;
            bu.owedBy(tempIou.name, tempIou.amount + amountL);
        }
        if (!found) {
            bu.owedBy(borrowerName, amountL);
        }
        return bu.build();
    }


    private User exchangeNeg(User lenderUser, User borrowerUser, double amount) {

        User.Builder bu = new User.Builder().setName(borrowerUser.name());
        String lenderName = lenderUser.name();
        double amountL = amount;

        boolean found = false;
        for (Iou tempIou : borrowerUser.owedBy()) {
            if (tempIou.name != lenderName) {
                bu.owedBy(tempIou.name, tempIou.amount);
                continue;
            }
            if (tempIou.amount > amountL) {
                bu.owedBy(tempIou.name, tempIou.amount - amountL);
                amountL = 0;
            }
            else {
                amountL -= tempIou.amount;
            }
        }

        for (Iou tempIou : borrowerUser.owes()) {
            if (tempIou.name != lenderName) {
                bu.owes(tempIou.name, tempIou.amount);
                continue;
            }
            found = true;
            bu.owes(tempIou.name, tempIou.amount + amountL);
        }
        if (!found) {
            bu.owes(lenderName, amountL);
        }
        
        
        return bu.build();
    }



}