package com.michaelRherman;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;

class JSON {
    /*This parse method was adapted from a JSON decoding tutorial
    located at http://www.tutorialspoint.com/json/json_java_example.htm
    Website Title: JSON with Java
    Article Title: Tutorials Point Simply Easy Learning
    Date Accessed: April 25, 2015
    */

     public static void JSONParse() {
        JSONParser parser=new JSONParser();
        String s = "[{\"artist_id\": \"ARRH63Y1187FB47783\", \"id\": \"SOUDIYM14B7C7B2D95\", \"artist_name\": \"Kanye West\", \"title\": \"All of the Lights\"}, {\"artist_id\": \"ARRH63Y1187FB47783\", \"id\": \"SODELAY13AD1ACC8CF\", \"artist_name\": \"Kanye West\", \"title\": \"All Of The Lights\"}, {\"artist_id\": \"ARRH63Y1187FB47783\", \"id\": \"SOHBKVU14509A9F6C3\", \"artist_name\": \"Kanye West\", \"title\": \"All Of The Lights\"}, {\"artist_id\": \"ARRH63Y1187FB47783\", \"id\": \"SOKGWES13D647BE466\", \"artist_name\": \"Kanye West\", \"title\": \"All Of The Lights\"}, {\"artist_id\": \"ARRH63Y1187FB47783\", \"id\": \"SOXIDRL13CCFBBC829\", \"artist_name\": \"Kanye West\", \"title\": \"All Of The Lights [LbLuke Rmx]\"}, {\"artist_id\": \"ARRH63Y1187FB47783\", \"id\": \"SOTJZSO12D857905F6\", \"artist_name\": \"Kanye West\", \"title\": \"All Of The Lights (Interlude)\"}, {\"artist_id\": \"ARRH63Y1187FB47783\", \"id\": \"SOTEMPJ13DB921F71F\", \"artist_name\": \"Kanye West\", \"title\": \"All of the Lights (Remix)\"}, {\"artist_id\": \"ARVCDGF12FE08689BA\", \"id\": \"SOGLUJD130516E0D00\", \"artist_name\": \"Made famous by Kanye West\", \"title\": \"All of the lights\"}]]";
        try{
            Object obj = parser.parse(s);
            JSONArray array = (JSONArray)obj;
            System.out.println(array.get(1));
            System.out.println();

            JSONObject obj2 = (JSONObject)array.get(1);
            System.out.println("Field \"1\"");
            System.out.println(obj2.get("1"));

        } catch (ParseException pe){
            System.out.println("position: " + pe.getPosition());
            System.out.println(pe);
        }
    }
}
