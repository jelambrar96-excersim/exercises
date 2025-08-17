import java.util.ArrayList;

class BuildTree {

    boolean checkRecords(ArrayList<Record> records) {

        int recordsSize = records.size();

        // check if all record id is not its own parent
        // check if all records have a parent id less your own id
        for (int i = 0; i < recordsSize; ++i) {
            Record r = records.get(i);
            int parentID = r.getParentId();
            int recordID = r.getRecordId();
            if (parentID == 0 && recordID == 0) { continue; } // root
            else if (r.getParentId() >= r.getRecordId()) {
                return false;
            }
        }

        // checf if each record id have a valid record id
        // check if each record have an unique id
        for (int i = 0; i < recordsSize; ++i) {
            int idRecordI = records.get(i).getRecordId();
            if (idRecordI < 0 || idRecordI >= recordsSize) {
                return false;
            }
            for (int j = i + 1; j < recordsSize; ++j) {
                int idRecordJ = records.get(j).getRecordId();
                if (idRecordI == idRecordJ) {
                    return false;
                }
            }
        }

        return true;
    }

    TreeNode buildTree(ArrayList<Record> records) throws InvalidRecordsException {
        
        if (!checkRecords(records)) {
            throw new InvalidRecordsException("Invalid Records");
        }

        int recordsSize = records.size();
        if (recordsSize == 0) return null;

        // create record sorted array
        Record[] arrayRecords = new Record[recordsSize];
        for (int i = 0; i < recordsSize; ++i) {
            Record record = records.get(i);
            int recordID = record.getRecordId();
            arrayRecords[recordID] = record;
        }

        // create all nodes
        TreeNode[] nodes = new TreeNode[recordsSize];
        for (int i = 0; i < recordsSize; ++i) {
            nodes[i] = new TreeNode(i);
        }

        // connect nodes with parent id
        for (int i = 1; i < recordsSize; ++i) {
            int parentId = arrayRecords[i].getParentId();
            nodes[parentId].getChildren().add(nodes[i]);
        }

        return nodes[0];
    }

}
