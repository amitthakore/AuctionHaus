package auctionhaus
import grails.plugin.jms.Queue
//C-1Receive message
class ListeningService {

        static final QUEUE_NAME = 'lQueue'

        static exposes = ['jms']

        @Queue(name = ListeningService.QUEUE_NAME)
        def receive(message) {

            log.info("Processing message ${message}")

        }


    }
