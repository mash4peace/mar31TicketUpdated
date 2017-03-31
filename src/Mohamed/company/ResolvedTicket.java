package Mohamed.company;

import java.util.Date;

/**
 * Created by mash4 on 3/30/2017.
 */
public class ResolvedTicket extends Ticket {
    protected  String resolution;

    protected String getResolution() {
        return resolution;
    }

    protected ResolvedTicket(String desc, int p, String rep, Date date, String res) {
        super(desc, p, rep, date);

        this.resolution = res;

    }

    @Override
    public String toString(){
        return("ID: " + this.ticketID + ";  Issue: " + this.description + "; Priority: " + 					this.priority + " Reported by: "
                + this.reporter + ";  Resolution for " + resolution +       ";   Reported on: " + this.dateReported);
    }
    }

