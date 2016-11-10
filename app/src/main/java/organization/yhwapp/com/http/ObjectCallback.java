package organization.yhwapp.com.http;

import java.lang.reflect.ParameterizedType;

public abstract class ObjectCallback<T> {

	private final Class<T> clazz;

	@SuppressWarnings("unchecked")
	public ObjectCallback() {
		ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
		clazz = (Class<T>) type.getActualTypeArguments()[0];
	}

	public Class<T> getDataClass() {
		return clazz;
	}

	public abstract void onSuccess(T modle);
	

	public abstract void onFailure(int errorCode, String errorString);
}
