
package com.MyGradleApp;

import android.app.Activity;
import android.widget.TextView;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class AppMenu extends Activity
{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        /* Create a TextView and set its text to "Hello world" */
        TextView  tv = new TextView(this);
        tv.setText("Hello Menu");
        setContentView(tv);
    }
   @Override
   public boolen onCreateOptionsMenu(Menu menu)
   {
        MenuItem menuitem1 = menu.add(0,0,1,"Menu1!");
        menuitem.setIcon();
        menuitem.onClick(){Toast.makeText(this,"You clicked the First Menu!",Toast.LENGTH_LONG).show();}
        super.onCreateOptionsMenu(menu);
    }
}
