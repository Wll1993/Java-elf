package testcase;

import com.javaelf.utils.JsonUtils;
import com.mifmif.common.regex.Generex;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Set;

public class RandomParameter {

    public static HashMap<String, Object> getStrParam(Integer leftBorder, Integer
            rightBorder, String paraName, String regular) {
        HashMap<String, Object> map = new HashMap();
        ArrayList<String> arrayList = new ArrayList();
        Random random = new Random();
        Generex generex = new Generex(regular);

        String empty = "";
        String spaceStr = " ";
        String leftStr = RandomStringUtils.randomAlphanumeric(leftBorder);
        String middleStr = RandomStringUtils.randomAlphanumeric(random.nextInt(rightBorder - leftBorder + 1) + leftBorder);
        String rightStr = RandomStringUtils.randomAlphanumeric(rightBorder);
        String moreStr = RandomStringUtils.randomAlphanumeric(rightBorder + 1);
        String regStr = generex.random();
        arrayList.add(empty);
        arrayList.add(spaceStr);
        arrayList.add(leftStr);
        arrayList.add(middleStr);
        arrayList.add(rightStr);
        arrayList.add(moreStr);
        arrayList.add(regStr);

        map.put(paraName, arrayList);
        map.put("middleStr", middleStr);
        return map;
    }

    public static HashMap<String, Object> getStrParam(Integer leftBorder, Integer
            rightBorder, String paraName) {
        HashMap<String, Object> map = new HashMap();
        ArrayList<String> arrayList = new ArrayList();
        Random random = new Random();

        String empty = "";
        String spaceStr = " ";
        String lessStr = null;
        String leftStr = null;
        if (leftBorder > 0) {
            lessStr = RandomStringUtils.randomAlphanumeric(leftBorder - 1);
        }
        if (leftBorder != 0) {
            leftStr = RandomStringUtils.randomAlphanumeric(leftBorder);
        }
        String middleStr = RandomStringUtils.randomAlphanumeric(random.nextInt(rightBorder - leftBorder + 1) + leftBorder);
        String rightStr = RandomStringUtils.randomAlphanumeric(rightBorder);
        String moreStr = RandomStringUtils.randomAlphanumeric(rightBorder + 1);

        arrayList.add(empty);
        arrayList.add(spaceStr);
        if (lessStr != null) {
            arrayList.add(lessStr);
        }
        if (leftStr != null) {
            arrayList.add(leftStr);
        }
        arrayList.add(middleStr);
        arrayList.add(rightStr);
        arrayList.add(moreStr);

        map.put(paraName, arrayList);
        map.put("middleStr", middleStr);
        return map;
    }

    /**
     * ??????????????????
     *
     * @param hashMaps
     * @return
     */
    public static ArrayList<HashMap<String, Object>> getOrgData(HashMap<String, Object>... hashMaps) {
        //???????????????
        String paraName = null;
        //finalArrayList????????????????????????
        ArrayList<HashMap<String, Object>> finalArrayList = new ArrayList();
        //????????????hashMaps??????????????????????????????hashMaps??????paraName???middleStr
        ArrayList<HashMap<String, Object>> lengthArrayList = new ArrayList();
        for (HashMap<String, Object> hashMap : hashMaps) {
            lengthArrayList.add(hashMap);
        }
        if(lengthArrayList.size()>1){
            for (int i = 0; i < lengthArrayList.size(); i++) {
                //  ArrayList<HashMap<String, Object>> arrayList = new ArrayList();
                //???????????????????????????i????????????????????????changehashMap
                HashMap<String, Object> changehashMap = lengthArrayList.get(i);
                //??????changehashMap????????????????????????
                Set<String> keys = changehashMap.keySet();
                for (String key : keys) {
                    if (key != "middleStr") {
                        paraName = key;
                    }
                }
                //???????????????changeArrayList,??????????????????????????????????????????
                ArrayList<String> changeArrayList = (ArrayList<String>) changehashMap.get(paraName);
                //????????????????????????middle??????????????????
                for (int j = 0; j < changeArrayList.size(); j++) {
                    HashMap<String, Object> orgHashMap = new HashMap();
                    //?????????lengthArrayList???hashMaps???middle???
                    for (int k = 0; k < lengthArrayList.size(); k++) {
                        //???????????????????????????
                        if (i != k) {
                            //???????????????map
                            HashMap<String, Object> otherHashMap = lengthArrayList.get(k);
                            String otherParaName = null;
                            //?????????????????????
                            Set<String> otherkeys = otherHashMap.keySet();
                            for (String otherkey : otherkeys) {
                                if (otherkey != "middleStr") {
                                    otherParaName = otherkey;
                                }
                            }
                            //????????????
                            Object middleStr = otherHashMap.get("middleStr");
                            //?????????????????????map
                            orgHashMap.put(otherParaName, middleStr);
                            String value = changeArrayList.get(j);
                            orgHashMap.put(paraName, value);
                        }
                    }
                    finalArrayList.add(orgHashMap);
                }
            }
            return finalArrayList;
        }else {
            HashMap<String, Object> changehashMap = lengthArrayList.get(0);
            Set<String> keys = changehashMap.keySet();
            for (String key : keys) {
                if (key != "middleStr") {
                    paraName = key;
                }
            }
            //???????????????changeArrayList,??????????????????????????????????????????
            ArrayList<String> changeArrayList = (ArrayList<String>) changehashMap.get(paraName);
            for (int i = 0; i <changeArrayList.size() ; i++) {
                HashMap<String, Object> orgHashMap = new HashMap();
                orgHashMap.put(paraName,changeArrayList.get(i));
                finalArrayList.add(orgHashMap);
            }
            return finalArrayList;
        }
    }

    /**
     * ???????????????????????????????????????
     *
     * @param hashMap
     * @return
     */
    public static ArrayList<String> requestBodyArrayList(ArrayList<HashMap<String, Object>> finalArrayList, HashMap<String, Object> hashMap) {
        ArrayList<String> arrayList = new ArrayList();

        for (int i = 0; i <finalArrayList.size() ; i++) {
            HashMap<String, Object>  objectHashMap = finalArrayList.get(i);
            objectHashMap.putAll(hashMap);
            String requestBody = JsonUtils.mapToJson(objectHashMap);
            arrayList.add(requestBody);
        }
        return arrayList;
    }


}
