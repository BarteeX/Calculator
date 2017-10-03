package sample;

import java.util.*;

/**
 * Created by BarteeX on 2017-09-30.
 */
public class IdController {
    private static HashMap<String, List<String>> tablesIdMap = new HashMap<>();

    private static List<Integer> prepareIds(List<String> ids) {
        List<Integer> returnList = new ArrayList<>();
        if(ids.size() > 0) {
            for (String id : ids) {
                returnList.add(Integer.parseInt(id));
            }
        }
        return returnList;
    }

    public static int addNewId(String tableName) {
        if(tablesIdMap.containsKey(tableName)) {
            List<String> ids = tablesIdMap.get(tableName);
            List<Integer> idsAsIntegers = prepareIds(ids);

            Integer maxId = Collections.max(idsAsIntegers);
            int id = maxId + 1;

            if(ids.contains(id)) {
                throw new IllegalArgumentException("ID =(" + id + ") is already used in table base " + tableName);
            } else {
                ids.add(String.valueOf(id));
                System.out.println("ID =(" + id + ") successful put to table " + tableName);
                return id;
            }
        } else {
            List<String> ids = new ArrayList<>();
            ids.add("1");
            tablesIdMap.put(tableName, ids);
            System.out.println("ID =(" + 1 + ") successful put to table " + tableName);
            return 1;
        }
    }

}
