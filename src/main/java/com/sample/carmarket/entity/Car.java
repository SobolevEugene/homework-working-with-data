package com.sample.carmarket.entity;

import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.UUID;

@JmixEntity
@Table(name = "CAR", indexes = {
		@Index(name = "IDX_CAR_MODEL", columnList = "MODEL_ID")
}, uniqueConstraints = {
		@UniqueConstraint(name = "IDX_CAR_REG_NUMBER", columnNames = {"REGISTRATION_NUMBER"})
})
@Entity
public class Car {
	@JmixGeneratedValue
	@Column(name = "ID", nullable = false)
	@Id
	private UUID id;

	@InstanceName
	@Length(min = 6, max = 6)
	@Column(name = "REGISTRATION_NUMBER", nullable = false)
	@NotNull
	private String registrationNumber;

	@NotNull
	@JoinColumn(name = "MODEL_ID", nullable = false)
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	private Model model;

	@Positive
	@Max(2030)
	@Min(1990)
	@Column(name = "PRODUCTION_YEAR")
	private Integer productionYear;

	@NotNull
	@Column(name = "STATUS", nullable = false)
	private String status;

	@PastOrPresent
	@Column(name = "SALE_DATE")
	private LocalDate saleDate;

	public void setProductionYear(Integer productionYear) {
		this.productionYear = productionYear;
	}

	public Integer getProductionYear() {
		return productionYear;
	}

	public LocalDate getSaleDate() {
		return saleDate;
	}

	public void setSaleDate(LocalDate saleDate) {
		this.saleDate = saleDate;
	}

	public CarStatus getStatus() {
		return status == null ? null : CarStatus.fromId(status);
	}

	public void setStatus(CarStatus status) {
		this.status = status == null ? null : status.getId();
	}

	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}

	public String getRegistrationNumber() {
		return registrationNumber;
	}

	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}
}