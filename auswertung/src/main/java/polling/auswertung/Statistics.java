package polling.auswertung;

import java.util.ArrayList;
import java.util.List;

public class Statistics {

    public static double mean (List<Integer> table)
    {
        int total = 0;

        for ( int i= 0;i < table.size(); i++)
        {
            int currentNum = table.get(i);
            total+= currentNum;
        }
        return total/table.size();
    }

    public static double sd (List<Integer> table)
    {
        double mean= mean(table);
        double temp =0;
        for ( int i= 0; i <table.size(); i++)
        {
            temp= Math.pow(i-mean, 2);
        }

        return Math.sqrt(mean( table));
    }

    public static void selectionSort(List<Integer> table)
    {
        int count = table.size();
        for(int pos = 0; pos < count - 1; pos++)
        {
            int locMin = pos;
            for(int i = pos + 1; i < count; i++)
            {
                if(table.get(i) < table.get(locMin))
                    locMin = i;
            }

            int temp = table.get(pos);
            table.set(pos, table.get(locMin) );
            table.set(locMin, temp);
        }
    }

}