import java.util.List;

class RelationshipComputer<T> {
    Relationship computeRelationship(List<T> firstList, List<T> secondList) {

        int lenFisrt = firstList.size(), lenSecond = secondList.size();

        if (lenFisrt == 0 && lenSecond == 0) return Relationship.EQUAL;
        if (lenFisrt == 0 && lenSecond > 0) return Relationship.SUBLIST;
        if (lenFisrt > 0 && lenSecond == 0) return Relationship.SUPERLIST; 

        if (lenFisrt == lenSecond) {
            for (int i = 0; i < lenFisrt; ++i) {
                if (firstList.get(i) != secondList.get(i)) {
                    return Relationship.UNEQUAL;
                }
            }
            return Relationship.EQUAL;
        }

        if (lenFisrt < lenSecond){
            for (int i = 0, limit = lenSecond - lenFisrt + 1; i < limit; ++i) {
                List<T> subListSecond = secondList.subList(i, i + lenFisrt);
                if (computeRelationship(firstList, subListSecond) == Relationship.EQUAL) {
                    return Relationship.SUBLIST;
                }
            }
            return Relationship.UNEQUAL;
        }

        return computeRelationship(secondList, firstList) == Relationship.SUBLIST ? 
                Relationship.SUPERLIST : Relationship.UNEQUAL;
    }
}
