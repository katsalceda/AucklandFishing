package rktechltd.aucklandfishing.db.daos.implementations;

import android.content.Context;
import android.database.Cursor;

import java.util.List;

import rktechltd.aucklandfishing.db.AucklandFishingDBHelper;
import rktechltd.aucklandfishing.db.daos.interfaces.ChecklistDAOInterface;
import rktechltd.aucklandfishing.models.Checklist;

/**
 * Created by romelyn on 30/05/2016.
 */
public class ChecklistDAO implements ChecklistDAOInterface {
    private AucklandFishingDBHelper db ;
    public ChecklistDAO(Context context){
        db = new AucklandFishingDBHelper(context);//initialize database with context
    }
    @Override
    public Checklist getCheckList(int checklistId) {
        return db.findChecklist(""+checklistId);
    }

    @Override
    public Cursor getAllCheckList() {
      return db.getAllChecklist();
    }

    @Override
    public boolean addCheckList(Checklist checklist) {
        return db.saveCheckList(checklist);
    }

    @Override
    public int getCheckListId(String title) {
       return db.findChecklistId(title);
    }
}
