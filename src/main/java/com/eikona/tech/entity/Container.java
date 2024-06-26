package com.eikona.tech.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

@Entity(name = "et_container")
public class Container extends Auditable<String> implements Serializable {
		
		private static final long serialVersionUID = 1L;
		
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
		@GenericGenerator(name = "native", strategy = "native")
		@Column(name = "id")
		private Long id;
		
		@Column(name = "boe_no")
		private String boeNo;
		
		@Column(name = "container_no")
		private String containerNo;
		
		@Column(name = "seal_no")
		private String sealNo;
		
		@Column(name = "type")
		private String type;
		
		@Column(name = "weight")
		private String weight;
		
		@Column(name = "size")
		private String size;
		
		@Column(name = "vessel")
		private String vessel;
		
		@Column(name = "voyage_no")
		private String voyageNo;
		
		@Column(name = "operator")
		private String operator;
		
		@Column(name = "origin")
		private String origin;
		
		@Column(name = "destination")
		private String destination;
		
		@Column(name = "container_type")
		private String containerType;
		
		@Column(name = "current_status")
		private String currentStatus;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getBoeNo() {
			return boeNo;
		}

		public void setBoeNo(String boeNo) {
			this.boeNo = boeNo;
		}

		public String getContainerNo() {
			return containerNo;
		}

		public void setContainerNo(String containerNo) {
			this.containerNo = containerNo;
		}

		public String getSealNo() {
			return sealNo;
		}

		public void setSealNo(String sealNo) {
			this.sealNo = sealNo;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public String getVessel() {
			return vessel;
		}

		public void setVessel(String vessel) {
			this.vessel = vessel;
		}

		public String getVoyageNo() {
			return voyageNo;
		}

		public void setVoyageNo(String voyageNo) {
			this.voyageNo = voyageNo;
		}

		public String getOperator() {
			return operator;
		}

		public void setOperator(String operator) {
			this.operator = operator;
		}

		public String getOrigin() {
			return origin;
		}

		public void setOrigin(String origin) {
			this.origin = origin;
		}

		public String getDestination() {
			return destination;
		}

		public void setDestination(String destination) {
			this.destination = destination;
		}

		public String getContainerType() {
			return containerType;
		}

		public void setContainerType(String containerType) {
			this.containerType = containerType;
		}

		public String getCurrentStatus() {
			String colorCode="/assets/images/";
			if("Ready For Booking".equalsIgnoreCase(currentStatus))
				colorCode+="Green.jpg";
			else if("Not Available To VBS".equalsIgnoreCase(currentStatus))
				colorCode+="Red.jpg";
			else if("Not Discharged From Vessel".equalsIgnoreCase(currentStatus))
				colorCode+="Amber.jpg";
			else if("Customs Clearing Pending".equalsIgnoreCase(currentStatus))
				colorCode+="cross.png";
			else if("Held By Authorities".equalsIgnoreCase(currentStatus))
				colorCode+="hand.png";
			else if("Already Booked Or Processed".equalsIgnoreCase(currentStatus))
					colorCode+="grey.png";
			return colorCode;
		}

		public void setCurrentStatus(String currentStatus) {
			this.currentStatus = currentStatus;
		}

		public String getWeight() {
			return weight;
		}

		public void setWeight(String weight) {
			this.weight = weight;
		}

		public String getSize() {
			return size;
		}

		public void setSize(String size) {
			this.size = size;
		}
		
		

}
