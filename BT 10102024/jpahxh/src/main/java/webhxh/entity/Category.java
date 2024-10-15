package webhxh.entity;

import java.io.Serializable;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="categories")
@NamedQuery(name="Category.findAll",query="SELECT c FROM Category c")
public class Category implements Serializable{


	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	
	@Column(name="Categoryid")
	private int categoryid;
	
	@Column(name="Categoryname", columnDefinition ="NVARCHAR(200) NOT NULL" )
	@NotEmpty(message = "Không được phép rỗng")
	private String categoryname;
	
	@Column(name="Image", columnDefinition ="NVARCHAR(500) NULL")
	private String image;
	
	@Column(name="Status")
	private int status ;
	
	@OneToMany(mappedBy = "category")
	private List<Video> videos;


	public Video addVideo(Video video) {
		getVideos().add(video);
		video.setCategory(this);
		return video;
	}
	public Video removeVideo(Video video) {
		getVideos().remove(video);
		video.setCategory(null);
		return video;
	}
	
	

}
