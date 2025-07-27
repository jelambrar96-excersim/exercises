import java.util.HashMap;
import java.util.Map;

public class DialingCodes {

    Map<Integer, String> map = new HashMap<Integer, String>();

    public Map<Integer, String> getCodes() {
        return this.map;
    }

    public void setDialingCode(Integer code, String country) {
        this.map.put(code, country);
    }

    public String getCountry(Integer code) {
        return this.map.get(code);
    }

    public void addNewDialingCode(Integer code, String country) {
        if (!this.map.containsKey(code) && !this.map.containsValue(country)) {
            this.map.put(code, country);
        }
    }

    public Integer findDialingCode(String country) {
        for( Map.Entry<Integer, String> entry: this.map.entrySet()) {
            if (country == entry.getValue()) {
                return entry.getKey();
            }
        }
        return null;
    }

    public void updateCountryDialingCode(Integer code, String country) {
        Integer oldCode = findDialingCode(country);
        if (oldCode != null ){
            this.map.remove(oldCode);
            this.map.put(code, country);
        }
    }
}
