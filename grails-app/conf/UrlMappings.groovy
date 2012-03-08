class UrlMappings {

	static mappings = {


        "/"(controller:'listing',action: 'index')
        "/$controller/$action?/$id?"

                {
			constraints {
				// apply constraints here
			}

               }

	//"/"(view:"/index")
		"500"(view:'/error')
	}
    }
