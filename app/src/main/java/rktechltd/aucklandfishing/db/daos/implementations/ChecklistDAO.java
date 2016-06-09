package rktechltd.aucklandfishing.db.daos.implementations;
import android.content.Context;
import android.database.Cursor;
import rktechltd.aucklandfishing.db.AucklandFishingDBHelper;
import rktechltd.aucklandfishing.db.daos.interfaces.ChecklistDAOInterface;
import rktechltd.aucklandfishing.models.Checklist;

/**
<<<<<<< HEAD
 * Auckland Fishing
 * @version 16/05/2016
 * @author Romelyn Ungab and Katrina Salceda
=======
 * A Checklist Data Access Object
 * Created by romelyn on 30/05/2016.
>>>>>>> origin/master
 */

public class ChecklistDAO implements ChecklistDAOInterface {
    private AucklandFishingDBHelper db ;
    /**
     * Constructor
     * @param context
     */
    public ChecklistDAO(Context context){
        db = new AucklandFishingDBHelper(context);//initialize database with context
    }
    /**
     * gets the Checklist given the id
     * @param checklistId
     * @return
     */
    @Override
    public Checklist getCheckList(int checklistId) {
        return db.findChecklist(""+checklistId);
    }

    /**
     * Gets all checklist
     * @return Cursor
     */
    @Override
    public Cursor getAllCheckList() {
      return db.getAllChecklist();
    }
    /**
     * adds new Checklist
     * @param checklist
     * @return
     */
    @Override
    public boolean addCheckList(Checklist checklist) {
        return db.saveCheckList(checklist);
    }

    /**
     * Gets the id given a title
     * @param title
     * @return
     */
    @Override
    public int getCheckListId(String title) {
       return db.findChecklistId(title);
    }
}
