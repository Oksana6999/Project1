package HomeWorkSeven;

public final class ApplicationGlobalState {

    private static ApplicationGlobalState INSTANCE;
    private String selectedCity = null;
    private final String API_KEY = "PY8GyFjaE4bMq9op53sHAQ22hOUc8oH8";

    private ApplicationGlobalState () {

    }

    public static ApplicationGlobalState getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ApplicationGlobalState();
        }
        return INSTANCE;
    }

    public String getSelectedCity () {
        return selectedCity;
    }
    public void setSelectedCity (String selectedCity) {
        this.selectedCity = selectedCity;
    }

    public String getAPI_KEY () {
        return this.API_KEY;
    }
}
