package com.kingston.mmorpg.framework.eventbus;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Subscriber {

	private Object listener;

	private Method method;

	public Subscriber(Object listener, Method method) {
		super();
		this.listener = listener;
		this.method = method;
	}

	public void handleEvent(BaseEvent event) throws InvocationTargetException {
		try {
			method.invoke(listener, event);
		} catch (IllegalArgumentException e) {
			throw new Error("Method rejected target/argument: " + event, e);
		} catch (IllegalAccessException e) {
			throw new Error("Method became inaccessible: " + event, e);
		} catch (InvocationTargetException e) {
			if (e.getCause() instanceof Error) {
				throw (Error) e.getCause();
			}
			throw e;
		}
	}

}
