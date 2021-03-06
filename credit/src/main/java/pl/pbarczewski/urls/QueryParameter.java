package pl.pbarczewski.urls;

// Enum zawierający dodatkowe parametry wykorzystywane przy zapytaniach typu "Get".
public enum QueryParameter {
	ID("?id={id}");
	
	private String ids;

	private QueryParameter(String ids) {
		this.ids = ids;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}
}
