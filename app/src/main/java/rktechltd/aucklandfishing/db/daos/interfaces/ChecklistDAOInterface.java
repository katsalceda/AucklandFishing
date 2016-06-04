package rktechltd.aucklandfishing.db.daos.interfaces;

import android.database.Cursor;

import java.util.List;

import rktechltd.aucklandfishing.models.Checklist;

/**
 * Created by romelyn on 30/05/2016.
 */
public interface ChecklistDAOInterface {
        Checklist getCheckList(int checklistId);
        Cursor getAllCheckList();
        boolean addCheckList(Checklist checklist);
        int getCheckListId(String title);
}
