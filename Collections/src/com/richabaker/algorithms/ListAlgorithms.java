package com.richabaker.algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListAlgorithms
{
    public static void ransomNote(String[] magazine, String[] note) {
        List<String> magazineList = new ArrayList<>(Arrays.asList(magazine));
        List<String> noteList = new ArrayList<>(Arrays.asList(note));

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
        if (found)
            System.out.print("Yes");
        else
            System.out.print("No");
    }
}
