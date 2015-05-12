package com.michaelRherman;

import com.github.jreddit.action.SubmitActions;
import com.github.jreddit.entity.User;
import com.github.jreddit.utils.restclient.HttpRestClient;
import com.github.jreddit.utils.restclient.RestClient;

public class Reddit {
    private static String redditUsername;
    private static String redditPW;

    public static void postReddit(String artist1, String artist2, String comparisonString, String toleranceSet){
        // Initialize REST Client
        RestClient restClient = new HttpRestClient();
        restClient.setUserAgent("bot/1.0 by name");

        // Connect the user
        User user = new User(restClient, redditUsername, redditPW);
        try {
            user.connect();
        } catch (Exception e) {
            System.out.println(e);
        }

        // Handle to SubmitActions, which offers the basic API functionality to submit comments and posts
        SubmitActions submitActions = new SubmitActions(restClient, user);

        // Submit a self post
        submitActions.submitSelfPost(redditUsername+" compared "+artist1+" and "+artist2, "For "+toleranceSet+" the results for "+comparisonString
                , "MashupTool", null, null);
    }

    public static void setRedditUsername(String redditUsername) {
        Reddit.redditUsername = redditUsername;
    }

    public static void setRedditPW(String redditPW) {
        Reddit.redditPW = redditPW;
    }
}

//All methods and calls adapted from https://github.com/jReddit/jReddit
//or https://github.com/jReddit/jReddit/tree/master/src/main/java/examples