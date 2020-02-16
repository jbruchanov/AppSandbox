package com.scurab.appsandbox.core

val <T> T?.ref get() : T = this ?: npe("Reference is null, most likely invalid lifecycle access!")