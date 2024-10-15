package webhxh.entity;

import java.io.Serializable;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="videos")
@NamedQuery(name="Video.findAll",query="SELECT v FROM Video v")
public class Video implements Serializable {


	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="VideoId")
	private int videoId;
	
	@Column(name="Active")
	private int active;
	
	@Column(name="Description", columnDefinition = "nvarchar(500) null")
	private String description;
	
	@Column(name="Poster", columnDefinition = "nvarchar(500) null")
	private String poster;
	
	@Column(name="Title", columnDefinition = "nvarchar(500) null")
	private String title;
	
	@Column(name="Videoclip", columnDefinition = "nvarchar(500) null")
	private String videoclip;
	
	@Column(name="Views")
	private int views;
	
//	@OneToMany(mappedBy ="video")
//	private List<Favorite> favorites;
//	
//	@OneToMany(mappedBy ="video")
//	private List<Share> shares;
	
	@ManyToOne
	@JoinColumn(name="categoryid")
	private Category category;


}
