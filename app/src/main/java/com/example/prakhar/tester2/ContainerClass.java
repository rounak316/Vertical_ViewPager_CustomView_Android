package com.example.prakhar.tester2;

import android.content.Context;
import android.view.View;

/**
 * Created by Prakhar on 9/26/2015.
 */
public class ContainerClass {

    public static int view_1=-1;
    public static int view_2=-1;
    public static int view_3=-1;

    Context context;




    public ContainerClass(Context context ,int rl1, int rl2, int rl3)
    {
        this.context = context;

        view_1 = rl1;
        view_2 = rl2;
        view_3 = rl3;

    }

    void GetChildren(int id)
    {



    }



}
