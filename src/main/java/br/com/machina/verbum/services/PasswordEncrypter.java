// Copyright 2010 Thiago H. de Paula Figueiredo
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package br.com.machina.verbum.services;

/**
 * Interface that defines a service that encodes passwords.
 * 
 * @author Thiago H. de Paula Figueiredo
 */
public interface PasswordEncrypter {
	
	/**
	 * Encodes a password if it isn't already encoded.
	 * 
	 * @param password a {@link String}. It cannot be null.
	 * @return a {@link String}.
	 */
	String encrypt(String password);

}
