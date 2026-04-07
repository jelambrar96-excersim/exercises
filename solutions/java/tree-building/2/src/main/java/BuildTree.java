import java.util.ArrayList;

class BuildTree {

    TreeNode buildTree(ArrayList<Record> records) throws InvalidRecordsException {
        
        int recordsSize = records.size();
        if (recordsSize == 0) return null;

        // check if all record id is not its own parent
        // check if all records have a parent id less your own id
        for (int i = 0; i < recordsSize; ++i) {
            Record r = records.get(i);
            int parentID = r.getParentId();
            int recordID = r.getRecordId();
            if (parentID == 0 && recordID == 0) { continue; } // root
            else if (r.getParentId() >= r.getRecordId()) {
                throw new InvalidRecordsException("Invalid Records");
            }
        }

        // checf if each record id have a valid record id
        // check if each record have an unique id
        for (int i = 0; i < recordsSize; ++i) {
            int idRecordI = records.get(i).getRecordId();
            if (idRecordI < 0 || idRecordI >= recordsSize) {
                throw new InvalidRecordsException("Invalid Records");
            }
            for (int j = i + 1; j < recordsSize; ++j) {
                int idRecordJ = records.get(j).getRecordId();
                if (idRecordI == idRecordJ) {
                    throw new InvalidRecordsException("Invalid Records");
                }
            }
        }

        // create all nodes
        TreeNode rootNode = null;
        TreeNode[] nodes = new TreeNode[recordsSize];
        for (int i = 0; i < recordsSize; ++i) {
            int recordID = records.get(i).getRecordId();
            nodes[i] = new TreeNode(recordID);
            if (recordID == 0) {
                rootNode = nodes[i];
            }
        }

        // connect nodes with parent id
        for (int i = 0; i < recordsSize; ++i) {
            Record ri = records.get(i);
            if (ri.getRecordId() == 0) { continue; } // root
            Integer parentId = ri.getParentId();
            for (int j = 0; j < recordsSize; ++j) {
                if (i == j) continue;
                Record rj = records.get(j);
                if (parentId == rj.getRecordId()) {
                    TreeNode parentNode = nodes[j];
                    parentNode.addChildrenNode(nodes[i]);
                    break;
                }  
            }
        }

        return rootNode;
    }

}
