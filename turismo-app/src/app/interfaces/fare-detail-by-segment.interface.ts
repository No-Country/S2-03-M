import { IncludedCheckedBags } from './included-checked-bags.interface';
export interface FareDetailBySegment {
  cabin: string;
  class: string;
  fareBasis: string;
  includedCheckedBags: IncludedCheckedBags;
  segmentId: string;
}
