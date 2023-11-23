package com.lopreti.university.adapters.advice;

import com.lopreti.university.domain.dtos.ErrorResponseDto;
import com.lopreti.university.domain.exception.address.AddressAlreadyExistsException;
import com.lopreti.university.domain.exception.address.AddressNotFoundException;
import com.lopreti.university.domain.exception.address.AddressUserExistsException;
import com.lopreti.university.domain.exception.classes.ClassAlreadyExistsException;
import com.lopreti.university.domain.exception.classes.ClassNotFoundException;
import com.lopreti.university.domain.exception.course.CourseAlreadyExistsException;
import com.lopreti.university.domain.exception.course.CourseNotFoundException;
import com.lopreti.university.domain.exception.course.CoursePeriodNotFoundException;
import com.lopreti.university.domain.exception.others.MoreThanOneUpdateException;
import com.lopreti.university.domain.exception.others.NoValidFieldException;
import com.lopreti.university.domain.exception.others.ValueCannotBeEmptyException;
import com.lopreti.university.domain.exception.others.WithoutFieldUpdateException;
import com.lopreti.university.domain.exception.student.StudentAlreadyExistsException;
import com.lopreti.university.domain.exception.student.StudentNotFoundException;
import com.lopreti.university.domain.exception.subject.SubjectAlreadyExistsException;
import com.lopreti.university.domain.exception.subject.SubjectNotFoundException;
import com.lopreti.university.domain.exception.teacher.TeacherAlreadyExistsException;
import com.lopreti.university.domain.exception.teacher.TeacherNotFoundException;
import com.lopreti.university.domain.exception.user.UserNotFoundException;
import com.lopreti.university.domain.exception.user.UserStatusNotFoundException;
import com.lopreti.university.domain.exception.user.email.EmailAlreadyExistsException;
import com.lopreti.university.domain.exception.user.email.EmailInvalidFormatException;
import com.lopreti.university.domain.exception.people.PeopleCategoryNotFoundException;
import com.lopreti.university.domain.exception.people.PeopleNotFoundException;
import com.lopreti.university.domain.exception.people.PeopleUserExistsException;
import com.lopreti.university.domain.exception.user.UserAddressExistsException;
import com.lopreti.university.domain.exception.user.UserIsRequiredException;
import com.lopreti.university.domain.exception.user.password.PasswordInvalidFormatException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

import static com.lopreti.university.domain.valueObjects.GlobalErrorCodes.FIELD_CANNOT_BE_NULL_ERROR_CODE;
import static com.lopreti.university.domain.valueObjects.GlobalErrorCodes.INVALID_REQUEST_BODY_ERROR_CODE;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(StudentNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleStudentNotFoundException(StudentNotFoundException ex) {
        return new ResponseEntity<>(new ErrorResponseDto(ex.getCode(), ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(TeacherNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleTeacherNotFoundException(TeacherNotFoundException ex) {
        return new ResponseEntity<>(new ErrorResponseDto(ex.getCode(), ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ClassNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleClassNotFoundException(ClassNotFoundException ex) {
        return new ResponseEntity<>(new ErrorResponseDto(ex.getCode(), ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AddressNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleAddressNotFoundException(AddressNotFoundException ex) {
        return new ResponseEntity<>(new ErrorResponseDto(ex.getCode(), ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AddressAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDto> handleAddressAlreadyExistsException(AddressAlreadyExistsException ex) {
        return new ResponseEntity<>(new ErrorResponseDto(ex.getCode(), ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MoreThanOneUpdateException.class)
    public ResponseEntity<ErrorResponseDto> handleMoreThanOneUpdateException(MoreThanOneUpdateException ex) {
        return new ResponseEntity<>(new ErrorResponseDto(ex.getCode(), ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(WithoutFieldUpdateException.class)
    public ResponseEntity<ErrorResponseDto> handleWithoutFieldUpdateException(WithoutFieldUpdateException ex) {
        return new ResponseEntity<>(new ErrorResponseDto(ex.getCode(), ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoValidFieldException.class)
    public ResponseEntity<ErrorResponseDto> handleNoValidFieldUpdateException(NoValidFieldException ex) {
        return new ResponseEntity<>(new ErrorResponseDto(ex.getCode(), ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ValueCannotBeEmptyException.class)
    public ResponseEntity<ErrorResponseDto> handleValueCannotBeEmptyException(ValueCannotBeEmptyException ex) {
        return new ResponseEntity<>(new ErrorResponseDto(ex.getCode(), ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(StudentAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDto> handleStudentAlreadyExistsException(StudentAlreadyExistsException ex) {
        return new ResponseEntity<>(new ErrorResponseDto(ex.getCode(), ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TeacherAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDto> handleTeacherAlreadyExistsException(TeacherAlreadyExistsException ex) {
        return new ResponseEntity<>(new ErrorResponseDto(ex.getCode(), ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleUserNotFoundException(UserNotFoundException ex) {
        return new ResponseEntity<>(new ErrorResponseDto(ex.getCode(), ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDto> handleEmailAlreadyExistsException(EmailAlreadyExistsException ex) {
        return new ResponseEntity<>(new ErrorResponseDto(ex.getCode(), ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EmailInvalidFormatException.class)
    public ResponseEntity<ErrorResponseDto> handleEmailInvalidFormatException(EmailInvalidFormatException ex) {
        return new ResponseEntity<>(new ErrorResponseDto(ex.getCode(), ex.getMessage(), ex.getAction()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PasswordInvalidFormatException.class)
    public ResponseEntity<ErrorResponseDto> handlePasswordInvalidFormatException(PasswordInvalidFormatException ex) {
        return new ResponseEntity<>(new ErrorResponseDto(ex.getCode(), ex.getMessage(), ex.getAction()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CourseAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDto> handleCourseAlreadyExistsException(CourseAlreadyExistsException ex) {
        return new ResponseEntity<>(new ErrorResponseDto(ex.getCode(), ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CourseNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleCourseNotFoundException(CourseNotFoundException ex) {
        return new ResponseEntity<>(new ErrorResponseDto(ex.getCode(), ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CoursePeriodNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handlePeriodNotFoundException(CoursePeriodNotFoundException ex) {
        return new ResponseEntity<>(new ErrorResponseDto(ex.getCode(), ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ClassAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDto> handleClassAlreadyExistsException(ClassAlreadyExistsException ex) {
        return new ResponseEntity<>(new ErrorResponseDto(ex.getCode(), ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PeopleNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handlePeopleNotFoundException(PeopleNotFoundException ex) {
        return new ResponseEntity<>(new ErrorResponseDto(ex.getCode(), ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SubjectNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleSubjectsNotFoundException(SubjectNotFoundException ex) {
        return new ResponseEntity<>(new ErrorResponseDto(ex.getCode(), ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponseDto> handleHttpMessageNotReadableException() {
    return new ResponseEntity<>(new ErrorResponseDto(INVALID_REQUEST_BODY_ERROR_CODE.getCode(),
            "Error reading the request body."), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDto> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        return new ResponseEntity<>(new ErrorResponseDto(FIELD_CANNOT_BE_NULL_ERROR_CODE.getCode(),
                Objects.requireNonNull(ex.getFieldError()).getField() + " cannot be null"), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SubjectAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDto> handleSubjectAlreadyExistsException(SubjectAlreadyExistsException ex) {
        return new ResponseEntity<>(new ErrorResponseDto(ex.getCode(), ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PeopleCategoryNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handlePeopleCategoryNotFoundException(PeopleCategoryNotFoundException ex) {
        return new ResponseEntity<>(new ErrorResponseDto(ex.getCode(), ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserStatusNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleUserStatusNotFoundException(UserStatusNotFoundException ex) {
        return new ResponseEntity<>(new ErrorResponseDto(ex.getCode(), ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserAddressExistsException.class)
    public ResponseEntity<ErrorResponseDto> handleUserAddressExistsException(UserAddressExistsException ex) {
        return new ResponseEntity<>(new ErrorResponseDto(ex.getCode(), ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AddressUserExistsException.class)
    public ResponseEntity<ErrorResponseDto> handleAddressUserExistsException(AddressUserExistsException ex) {
        return new ResponseEntity<>(new ErrorResponseDto(ex.getCode(), ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserIsRequiredException.class)
    public ResponseEntity<ErrorResponseDto> handleUserIsRequiredException(UserIsRequiredException ex) {
        return new ResponseEntity<>(new ErrorResponseDto(ex.getCode(), ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PeopleUserExistsException.class)
    public ResponseEntity<ErrorResponseDto> handlePeopleUserExistsException(PeopleUserExistsException ex) {
        return new ResponseEntity<>(new ErrorResponseDto(ex.getCode(), ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

}
