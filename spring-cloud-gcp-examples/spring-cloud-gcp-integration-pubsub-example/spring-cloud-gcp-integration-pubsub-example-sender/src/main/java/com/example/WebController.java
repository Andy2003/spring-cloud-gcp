/*
 *  Copyright 2017 original author or authors.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

/**
 * @author João André Martins
 */
@RestController
public class WebController {

	@Autowired
	private SenderApplication.PubsubOutboundGateway messagingGateway;

	/**
	 * Posts a message to a Google Cloud Pub/Sub topic, through Spring's messaging gateway, and
	 * redirects the user to the home page.
	 *
	 * @param message the message posted to the Pub/Sub topic
	 */
	@PostMapping("/postMessage")
	public RedirectView postMessage(@RequestParam("message") String message) {
		this.messagingGateway.sendToPubsub(message);
		return new RedirectView("/");
	}
}
