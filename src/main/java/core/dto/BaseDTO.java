package core.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class BaseDTO {

    private String testDataFilePath = System.getProperty("user.dir") + "/DataProvider/test_data.json";

    @JsonAlias("test_data")
    @SerializedName("test_data")
    public String testData;
}
