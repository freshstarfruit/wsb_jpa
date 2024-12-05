INSERT INTO ADDRESS (id, city, address_line1, address_line2, postal_code) VALUES
    (1, 'New York', '123 Main St', 'Apt 1', '10001'),
    (2, 'Los Angeles', '456 Elm St', NULL, '90001'),
    (3, 'San Francisco', '789 Oak St', 'Suite 5', '94102'),
    (4, 'Chicago', '321 Pine St', NULL, '60601');

INSERT INTO PATIENT (id, first_name, last_name, telephone_number, email, patient_number, date_of_birth, address_id) VALUES
    (1, 'John', 'Doe', '1234567890', 'john.doe@example.com', 'P001', '1980-01-01', 1),
    (2, 'Jane', 'Smith', '0987654321', 'jane.smith@example.com', 'P002', '1990-02-02', 2);

INSERT INTO DOCTOR (id, first_name, last_name, telephone_number, email, doctor_number, specialization, address_id) VALUES
    (1, 'Alice', 'Brown', '2223334444', 'alice.brown@example.com', 'D001', 'SURGEON', 3),
    (2, 'Bob', 'Johnson', '5556667777', 'bob.johnson@example.com', 'D002', 'PEDIATRICIAN', 4);

INSERT INTO VISIT (id, description, time, patient_id, doctor_id) VALUES
    (1, 'Routine check-up', '2024-12-03T10:00:00', 1, 1),
    (2, 'Follow-up visit', '2024-12-04T14:00:00', 2, 1),
    (3, 'Vaccination', '2024-12-05T11:00:00', 2, 2);

INSERT INTO MEDICAL_TREATMENT (id, description, type, visit_id) VALUES
    (1, 'Blood Test', 'LAB_TEST', 1),
    (2, 'X-Ray', 'IMAGING', 1),
    (3, 'Prescription', 'MEDICATION', 2);
