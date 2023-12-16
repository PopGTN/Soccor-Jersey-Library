package info.hccis.SoccorJersey.util.api;

/**
 * POJO to hold attributes needed to work with the api
 * @author Joshua Mckenna
 * @since 20231106
 */
public class ApiDetail {
    private String category;
    private String txtToSearch;
    private int number;
    private String output;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTxtToSearch() {
        return txtToSearch;
    }

    public void setTxtToSearch(String txtToSearch) {
        this.txtToSearch = txtToSearch;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }
}
