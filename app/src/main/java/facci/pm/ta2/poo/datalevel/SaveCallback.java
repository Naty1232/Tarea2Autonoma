package facci.pm.ta2.poo.datalevel;

public interface SaveCallback<DataObject> {
    public void done(DataObject object, DataException e);
}