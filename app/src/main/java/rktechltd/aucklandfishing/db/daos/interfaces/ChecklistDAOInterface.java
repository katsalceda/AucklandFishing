package rktechltd.aucklandfishing.db.daos.interfaces;
import android.database.Cursor;
import rktechltd.aucklandfishing.models.Checklist;

/**
 * Auckland Fishing
 * @version 16/05/2016
 * @author Romelyn Ungab and Katrina Salceda
 */

public interface ChecklistDAOInterface {
        Checklist getCheckList(int checklistId);
        Cursor getAllCheckList();
        boolean addCheckList(Checklist checklist);
        int getCheckListId(String title);
}
