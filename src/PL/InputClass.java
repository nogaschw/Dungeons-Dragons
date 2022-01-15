package PL;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputClass implements Input{


   List<String> a= Arrays.asList("w","a","s","d","e","q");

    public String getAction()
    {
        boolean validInput=false;
        String input="";
        Scanner s= new Scanner(System.in);
        while (!validInput)
        {
            input=s.nextLine();
            validInput=a.contains(input);
        }
        return input.toString();
    }
}
