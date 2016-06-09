package rktechltd.aucklandfishing.db.daos.interfaces;

import android.database.Cursor;

import rktechltd.aucklandfishing.models.Checklist;

/**
 * An interface for Checklist Data Access Object
 * Created by romelyn on 30/05/2016.
 */
public interface ChecklistDAOInterface {
        Checklist getCheckList(int checklistId);
        Cursor getAllCheckList();
        boolean addCheckList(Checklist checklist);
        int getCheckListId(String title);
}
