package facci.pm.ta2.poo.datalevel;


public interface GetCallback<DataObject> {
    public void done(DataObject object, DataException e);
}
