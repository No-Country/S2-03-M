import { Itinerary } from './itinerary.interface';
import { Price } from './price.interface';
import { PricingOptions } from './pricing-options.interface';
import { TravelerPricing } from './traveler-pricing.interface';

export interface Flight {
  id: number;
  instantTicketingRequired: boolean;
  itineraries: Itinerary[];
  lastTicketingDate: Date;
  nonHomogeneous: boolean;
  numberOfBookableSeats: number;
  oneWay: boolean;
  price: Price;
  pricingOptions: PricingOptions;
  source: string;
  travelerPricings: TravelerPricing[];
  type: string;
  validatingAirlineCodes: string[];
}
