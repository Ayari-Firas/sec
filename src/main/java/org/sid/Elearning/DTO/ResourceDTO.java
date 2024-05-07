package org.sid.Elearning.DTO;
import lombok.Data;
@Data
public class ResourceDTO {

    private String title;
    private String description;
    private String niveau; // French for "level"
    private String url;
    private String type;
public ResourceDTO(String title,String description,String url){
    this.title=title;
    this.description=description;
    this.url=url;
}

}

