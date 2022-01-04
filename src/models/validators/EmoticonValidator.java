package models.validators;

import java.util.ArrayList;
import java.util.List;

import models.Emoticon;

public class EmoticonValidator {
    public static List<String> validate(Emoticon e) {
        List<String> errors = new ArrayList<String>();

        String emoticon_error = _validateEmoticon(e.getEmoticon());
        if(!emoticon_error.equals("")) {
            errors.add(emoticon_error);
        }

        return errors;
    }

    private static String _validateEmoticon(String emoticon) {
        if(emoticon == null || emoticon.equals("")) {
            return "顔文字を入力してください。";
        }

        return "";
    }
}
