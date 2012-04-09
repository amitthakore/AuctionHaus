class UrlMappings {

	static mappings = {

     //The main landing page is the first page a user sees when they come to AuctionHaus.
        "/"(controller:'listing',action: 'index')
        "/$controller/$action?/$id?"

                {
			constraints {
				// apply constraints here
			}

               }

	//"/"(view:"/index")
		"500"(view:'/error')

        "/login/$action?"(controller: "login")
        "/logout/$action?"(controller: "logout")

	}
    }
