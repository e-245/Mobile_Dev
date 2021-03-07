#include <jni.h>
#include <string>

extern "C"
JNIEXPORT jstring JNICALL
Java_com_example_bank_MainActivity_baseUrlFromJNI(
        JNIEnv* env,
        jclass clazz) {
    std::string baseURL = "https://60102f166c21e10017050128.mockapi.io/labbbank/accounts";
    return env->NewStringUTF(baseURL.c_str());
}
