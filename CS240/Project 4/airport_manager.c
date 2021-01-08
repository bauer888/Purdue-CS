typedef struct Airport {
	char* name;
	int numDestinations;
	struct Airport ** destinations;
} Airport;

typedef struct Airline {
	Airport** airports;
	int numAirports;
	int maxAirports;
} Airline;

void InitializationAirline(Airline* airline, int maxAirportsN) {
	
}
