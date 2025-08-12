import java.util.List;
import java.util.Objects;
import java.util.LinkedList;

class Flattener {

    public List<Object> flatten(List<?> list) {
        return flatten(list, true);
    }

    public List<Object> flatten(List<?> list, boolean skipNull) {
        List<Object> out = new LinkedList<Object>();
        for (Object o: list) {
            if (!(o instanceof List)) {
                if (Objects.nonNull(o) || !skipNull) out.add(o);
                continue;
            }
            List<Object> tempFlattenList = flatten((List<?>)o);
            out.addAll(tempFlattenList);
        }
        return out;
    }

}
