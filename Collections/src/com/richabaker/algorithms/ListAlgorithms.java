package com.richabaker.algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListAlgorithms
{
    public static void ransomNote(String[] magazine, String[] note) {
        List<String> magazineList = new ArrayList<String>(Arrays.asList(magazine));
        List<String> noteList = new ArrayList<String>(Arrays.asList(note));

        //for (String word : magazine)
        //    magazineList.add(word);
        //for (String word : note)
        //    noteList.add(word);
       // magazineList = Arrays.asList(magazine);
        //noteList = Arrays.asList(note);

        boolean found = true;
        for (String noteWord : noteList)
        {
            if (magazineList.contains(noteWord))
            {
                magazineList.remove(noteWord);
            }
            else
            {
                found = false;
                break;
            }
        }
        if (found == true)
            System.out.print("Yes");
        else
            System.out.print("No");



    }

}
