import { Aircraft } from './aircraft.interface';
import { Arrival } from './arrival.interface';
import { Departure } from './departure.interface';
import { Operating } from './operating.interface';
export interface Segment {
  aircraft: Aircraft;
  arrival: Arrival;
  blacklistedInEU: boolean;
  carrierCode: string;
  departure: Departure;
  duration: string;
  id: number;
  number: number;
  numberOfStops: number;
  operating: Operating;
}
