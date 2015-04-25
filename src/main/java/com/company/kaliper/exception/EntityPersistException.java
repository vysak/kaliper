package com.company.kaliper.exception;

public class EntityPersistException extends Exception{
	private static final long serialVersionUID = 4867645708199153376L;

    public EntityPersistException() {
    }

    public EntityPersistException(String arg0) {
        super(arg0);
    }

    public EntityPersistException(Throwable arg0) {
        super(arg0);
    }

    public EntityPersistException(String arg0, Throwable arg1) {
        super(arg0, arg1);
    }

    public EntityPersistException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
        super(arg0, arg1, arg2, arg3);
    }

}
