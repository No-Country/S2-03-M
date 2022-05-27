import { Price } from './price.interface';
import { FareDetailBySegment } from './fare-detail-by-segment.interface';
export interface TravelerPricing {
  fareDetailsBySegment: FareDetailBySegment[];
  fareOption: string;
  price: Price;
  travelerId: number;
  travelerType: string;
}
