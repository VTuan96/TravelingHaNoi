package com.example.dtvta.testrestfulapi.common;

import java.util.ArrayList;

/**
 * Created by vutuan on 28/07/2017.
 */

public class StringFormatter {
    public String formatStringUrl(String str){
        String result="";

        str.concat(str);
        String arrStr[]=str.split(" ");
        ArrayList<String> temp=new ArrayList<>();
        for (int i=0;i<arrStr.length;i++){
            arrStr[i].concat(arrStr[i]);
            if (arrStr[i].length()!=0){
                temp.add(arrStr[i]);
            }
        }
        for (int i=0;i<temp.size()-1;i++){
            result+=temp.get(i)+"+";
        }
        result+=temp.get(temp.size()-1);
        System.out.println(result);

        return result;

    }
}
