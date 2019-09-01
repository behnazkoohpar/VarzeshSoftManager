package com.noor.payment.data.model.api;


public class BaseResponse<T> {

    protected ErrorDetails ErrorDetails;

    public T BodyResult;

    public T getBodyResult() {
        return BodyResult;
    }

    public void setBodyResult(T bodyResult) {
        BodyResult = bodyResult;
    }

    public ErrorDetails getErrorDetails() {
        return ErrorDetails;
    }

    public void setErrorDetails(ErrorDetails errorDetails) {
        ErrorDetails = errorDetails;
    }

//    public static class ParcelWrapperConverter implements ParcelConverter {
//
//        @Override
//        public void toParcel(Object input, android.os.Parcel parcel) {
//            parcel.writeParcelable(Parcels.wrap(input), 0);
//        }
//
//        @Override
//        public Object fromParcel(android.os.Parcel parcel) {
//            return ((ParcelWrapper)parcel.readParcelable(ParcelWrapperConverter.class.getClassLoader())).getParcel();
//        }
//    }
}
