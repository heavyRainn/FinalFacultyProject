package by.epam.t7.entity;

public class Feedback{

	private int id;
	private int studentId;
	private int trainingId;
	private String description;
	private int mark;

	public Feedback() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public int getTrainingId() {
		return trainingId;
	}

	public void setTrainingId(int trainingId) {
		this.trainingId = trainingId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getMark() {
		return mark;
	}

	public void setMark(int mark) {
		this.mark = mark;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + id;
		result = prime * result + mark;
		result = prime * result + studentId;
		result = prime * result + trainingId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Feedback other = (Feedback) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id != other.id)
			return false;
		if (mark != other.mark)
			return false;
		if (studentId != other.studentId)
			return false;
		if (trainingId != other.trainingId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Feedback [id=" + id + ", studentId=" + studentId + ", trainingId=" + trainingId + ", description="
				+ description + ", mark=" + mark + "]";
	}

}
