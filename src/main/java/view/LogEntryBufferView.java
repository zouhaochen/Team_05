package view;

import model.LogEntryBuffer;
import model.Observable;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * view for log Entry buffer
 */
public class LogEntryBufferView implements Observer {


    /**
     * view tag
     */
    int d_ObsTag = 3;

    String d_FilePath="log/Log.txt";

    Calendar d_Calendar;
    SimpleDateFormat d_SimpleDateFormat;

    /**
     * obs constructor
     *
     * @param p_LogEntryBuffer model logger
     */
    public LogEntryBufferView(LogEntryBuffer p_LogEntryBuffer) {
        p_LogEntryBuffer.attachObs(this);
        d_SimpleDateFormat = new SimpleDateFormat("HH:mm:ss");
    }


    /**
     * interface update function
     *
     * @param p_observable
     */
    @Override
    public void update(Observable p_observable) {
        d_Calendar = Calendar.getInstance();
        PrintWriter l_Out = null;
        try {;
            l_Out = new PrintWriter(new BufferedWriter(new FileWriter(d_FilePath, true)));
            l_Out.println(d_SimpleDateFormat.format(d_Calendar.getTime()) );
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (l_Out != null) {
                l_Out.close();
            }
        }
        System.out.println("log file updated");

    }


    /**
     * tag getter
     *
     * @return obs tag string
     */
    @Override
    public int getTag() {
        return d_ObsTag;
    }
}
