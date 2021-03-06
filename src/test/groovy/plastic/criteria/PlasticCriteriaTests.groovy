package plastic.criteria

import static plastic.criteria.PlasticCriteria.*
import grails.test.mixin.Mock
import org.junit.Before
import org.junit.Test

@Mock([Artist, City, Portrait])
class PlasticCriteriaTests extends CriteriaDocTests {

	def saintPeter

	@Before
	void setUp() {
		mockCriteria([Artist, Portrait])
		saintPeter = PlasticCriteria._SaintPeter
	}

	// next release 1.3
	@Test
	void test_why_not_in_result() {
		def artitst = new Artist(name: 'Brilhante').save()
		def soleil = new Portrait(artist: artitst, name: 'Soleil levant', value: 1.0).save()

		def results = Portrait.withCriteria{
			eq('name', 'not exists')
		}
		assert saintPeter.didYouSay("    eq('name', 'not exists') == false")
	}

	@Test
	void test_Saint_Peter_should_say_Hey_the_list_is_empty() {
		def results = Portrait.withCriteria{
			eq('name', 'not exists')
		}
		assert saintPeter.didYouSay('Hey the Portrait.list() is empty!')
	}

}