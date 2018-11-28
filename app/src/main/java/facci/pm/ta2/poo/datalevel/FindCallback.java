package facci.pm.ta2.poo.datalevel;

import java.util.ArrayList;


public interface FindCallback<DataObject> {
    public void done(ArrayList<DataObject> objects, DataException e);
}
